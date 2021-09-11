package com.edusys.dao;

import com.edusys.utils.jdbcHelper;
import com.edusys.entity.NguoiHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
    String INSERT_SQL = "INSERT INTO NguoiHoc(MaNH, HoTen, GioiTinh,NgaySinh,DienThoai,Email,GhiChu,MaNV)VALUES(?,?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen = ?,GioiTinh = ?,NgaySinh = ?,DienThoai = ?, Email = ?,ghiChu = ?,MaNV = ?,NgayDK = ? WHERE MaCD = ?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH =?";
    String SELECT_ALL_SQL="SELECT *FROM NguoiHoc";
    String SELETE_BY_ID_SQL = "SELECT * FROM NguoiHoc WHERE MaNH =?";

    public List<NguoiHoc> selectByKeyword(String keyWord){
        String sql = "select * from nguoiHoc where hoten like ?";
        return this.selectBySql(sql, "%"+keyWord+"%");
    }
        public List<NguoiHoc> selectNotInCourse(int makh, String keyword){
        String sql = "SELECT * FROM NguoiHoc WHERE HOTEN LIKE ? AND MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH = ?)";
        return this.selectBySql(sql,"%"+keyword+"%",makh);
    }
        public List<Integer> selectYear(){
            String sql = "SELECT DISTINCT year(NgayKG) FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list = new ArrayList<>();
        try{
            ResultSet rs = jdbcHelper.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        }
        catch(SQLException ex){
            throw new RuntimeException(ex);
        }
        }
    @Override
    public void insert(NguoiHoc enity) {
        try { 
            jdbcHelper.update(INSERT_SQL, enity.getMaNH(),enity.getHoTen(),enity.isGioiTinh(),enity.getNgaySinh(),enity.getDienThoai(),enity.getEmail(),enity.getGhiChu(),enity.getMaNV());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NguoiHoc enity) {
                try {
            jdbcHelper.update(UPDATE_SQL, enity.getHoTen(),enity.isGioiTinh(),enity.getNgaySinh(),enity.getDienThoai(),enity.getEmail(),enity.getGhiChu(),enity.getMaNV(),enity.getNgayDK(),enity.getMaNH());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = this.selectBySql(SELETE_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try{
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                NguoiHoc enity = new NguoiHoc();
                enity.setMaNH(rs.getString("MaNH"));
                enity.setHoTen(rs.getString("hoTen"));
                enity.setGioiTinh(rs.getBoolean("gioitinh"));
                enity.setNgaySinh(rs.getDate("NgaySinh"));
                enity.setDienThoai(rs.getString("DienThoai"));
                enity.setEmail(rs.getString("Email"));
                enity.setGhiChu(rs.getString("GhiChu"));
                enity.setMaNV(rs.getString("Manv"));
                enity.setNgayDK(rs.getDate("NgayDK"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
}
