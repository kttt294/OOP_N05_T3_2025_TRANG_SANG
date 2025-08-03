// Custom JavaScript for Cinema Management System

$(document).ready(function() {
    
    // Initialize tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
    
    // Initialize popovers
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });
    
    // Add fade-in animation to cards
    $('.card').addClass('fade-in');
    
    // Add slide-in animation to tables
    $('.table').addClass('slide-in');
    
    // Auto-hide alerts after 5 seconds
    setTimeout(function() {
        $('.alert').fadeOut('slow');
    }, 5000);
    
    // Confirm delete actions
    $('.btn-delete').click(function(e) {
        if (!confirm('Bạn có chắc chắn muốn xóa?')) {
            e.preventDefault();
        }
    });
    
    // Form validation
    $('form').submit(function() {
        var isValid = true;
        
        // Check required fields
        $(this).find('[required]').each(function() {
            if (!$(this).val()) {
                $(this).addClass('is-invalid');
                isValid = false;
            } else {
                $(this).removeClass('is-invalid');
            }
        });
        
        // Email validation
        $(this).find('input[type="email"]').each(function() {
            var email = $(this).val();
            var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (email && !emailRegex.test(email)) {
                $(this).addClass('is-invalid');
                isValid = false;
            }
        });
        
        // Phone number validation
        $(this).find('input[name*="phone"], input[name*="sdt"]').each(function() {
            var phone = $(this).val();
            var phoneRegex = /^[0-9]{10,11}$/;
            if (phone && !phoneRegex.test(phone)) {
                $(this).addClass('is-invalid');
                isValid = false;
            }
        });
        
        if (!isValid) {
            e.preventDefault();
            showAlert('Vui lòng kiểm tra lại thông tin!', 'danger');
        }
        
        return isValid;
    });
    
    // Search functionality
    $('#searchInput').on('keyup', function() {
        var value = $(this).val().toLowerCase();
        $('table tbody tr').filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
    
    // Sort table columns
    $('.sortable').click(function() {
        var table = $(this).parents('table').eq(0);
        var rows = table.find('tr:gt(0)').toArray().sort(comparer($(this).index()));
        this.asc = !this.asc;
        if (!this.asc) {
            rows = rows.reverse();
        }
        for (var i = 0; i < rows.length; i++) {
            table.append(rows[i]);
        }
    });
    
    // Date picker initialization
    $('input[type="date"]').each(function() {
        $(this).attr('max', new Date().toISOString().split('T')[0]);
    });
    
    // Price formatting
    $('.price-input').on('input', function() {
        var value = $(this).val().replace(/[^\d]/g, '');
        if (value) {
            $(this).val(parseInt(value).toLocaleString('vi-VN'));
        }
    });
    
    // Character counter for textareas
    $('textarea[maxlength]').on('input', function() {
        var maxLength = $(this).attr('maxlength');
        var currentLength = $(this).val().length;
        var remaining = maxLength - currentLength;
        
        if (!$(this).next('.char-counter').length) {
            $(this).after('<small class="char-counter text-muted"></small>');
        }
        
        $(this).next('.char-counter').text(remaining + ' ký tự còn lại');
        
        if (remaining < 0) {
            $(this).next('.char-counter').addClass('text-danger');
        } else {
            $(this).next('.char-counter').removeClass('text-danger');
        }
    });
    
    // Loading spinner for form submissions
    $('form').on('submit', function() {
        var submitBtn = $(this).find('button[type="submit"]');
        if (submitBtn.length) {
            submitBtn.prop('disabled', true);
            submitBtn.html('<span class="spinner-border spinner-border-sm me-2"></span>Đang xử lý...');
        }
    });
    
    // Modal enhancements
    $('.modal').on('show.bs.modal', function() {
        $(this).find('.modal-content').addClass('fade-in');
    });
    
    // Tab navigation with URL hash
    $('a[data-bs-toggle="tab"]').on('shown.bs.tab', function(e) {
        window.location.hash = $(e.target).attr('href');
    });
    
    // Handle back button for tabs
    if (window.location.hash) {
        $('a[href="' + window.location.hash + '"]').tab('show');
    }
    
    // Print functionality
    $('.btn-print').click(function() {
        window.print();
    });
    
    // Export to CSV
    $('.btn-export').click(function() {
        var table = $($(this).data('table'));
        var csv = [];
        var rows = table.find('tr');
        
        for (var i = 0; i < rows.length; i++) {
            var row = [], cols = $(rows[i]).find('td, th');
            for (var j = 0; j < cols.length; j++) {
                row.push('"' + $(cols[j]).text().replace(/"/g, '""') + '"');
            }
            csv.push(row.join(','));
        }
        
        var csvContent = 'data:text/csv;charset=utf-8,' + csv.join('\n');
        var encodedUri = encodeURI(csvContent);
        var link = document.createElement('a');
        link.setAttribute('href', encodedUri);
        link.setAttribute('download', 'export.csv');
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    });
});

// Utility functions
function showAlert(message, type) {
    var alertHtml = '<div class="alert alert-' + type + ' alert-dismissible fade show" role="alert">' +
                    '<i class="fas fa-info-circle me-2"></i>' + message +
                    '<button type="button" class="btn-close" data-bs-dismiss="alert"></button>' +
                    '</div>';
    
    $('.container').first().prepend(alertHtml);
    
    setTimeout(function() {
        $('.alert').fadeOut('slow');
    }, 5000);
}

function comparer(index) {
    return function(a, b) {
        var valA = getCellValue(a, index), valB = getCellValue(b, index);
        return $.isNumeric(valA) && $.isNumeric(valB) ? valA - valB : valA.localeCompare(valB);
    };
}

function getCellValue(row, index) {
    return $(row).children('td').eq(index).text();
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
    }).format(amount);
}

function formatDate(dateString) {
    var date = new Date(dateString);
    return date.toLocaleDateString('vi-VN');
}

function formatDateTime(dateTimeString) {
    var date = new Date(dateTimeString);
    return date.toLocaleString('vi-VN');
}

// AJAX helper functions
function ajaxGet(url, successCallback, errorCallback) {
    $.ajax({
        url: url,
        type: 'GET',
        success: successCallback,
        error: errorCallback || function(xhr, status, error) {
            showAlert('Lỗi: ' + error, 'danger');
        }
    });
}

function ajaxPost(url, data, successCallback, errorCallback) {
    $.ajax({
        url: url,
        type: 'POST',
        data: data,
        success: successCallback,
        error: errorCallback || function(xhr, status, error) {
            showAlert('Lỗi: ' + error, 'danger');
        }
    });
}

// Chart.js integration (if needed)
function createChart(canvasId, type, data, options) {
    var ctx = document.getElementById(canvasId).getContext('2d');
    return new Chart(ctx, {
        type: type,
        data: data,
        options: options
    });
} 