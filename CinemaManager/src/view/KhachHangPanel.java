import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class KhachHangPanel extends JPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public KhachHangPanel() {
        setLayout(new BorderLayout());

        // Table
        String[] columns = {"CCCD", "Tên", "Tuổi", "SĐT", "Email", "Giới tính"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton btnLoad = new JButton("Tải danh sách");
        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");

        buttonPanel.add(btnLoad);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Load data
        btnLoad.addActionListener(e -> loadKhachHang());

        // Thêm, sửa, xóa: chỉ hiển thị thông báo mẫu
        btnAdd.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng thêm khách hàng!"));
        btnEdit.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng sửa khách hàng!"));
        btnDelete.addActionListener(e -> JOptionPane.showMessageDialog(this, "Chức năng xóa khách hàng!"));
    }

    private void loadKhachHang() {
        tableModel.setRowCount(0);
        // ArrayList<KhachHang> ds = KhachHang.Read();
        // for (KhachHang kh : ds) {
        //     tableModel.addRow(new Object[]{
        //         kh.getCCCD(), kh.getTen(), kh.getTuoi(), kh.getSdt(), kh.getEmail(), kh.getGioiTinh()
        //     });
        // }
        // Dữ liệu mẫu (bạn thay bằng dữ liệu thực tế khi kết nối model)
        tableModel.addRow(new Object[]{"123456789", "Nguyễn Văn A", 25, "0123456789", "a@gmail.com", "Nam"});
        tableModel.addRow(new Object[]{"987654321", "Trần Thị B", 30, "0987654321", "b@gmail.com", "Nữ"});
    }
} 