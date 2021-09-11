package com.edusys.dao;

import com.edusys.utils.jdbcHelper;
import com.edusys.entity.NhanVien;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
    String INSERT_SQL = "INSERT INTO NhanVien(MaNV, MatKhau, HoTen,VaiTro)VALUES(?,?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET MatKhau = ?,HoTen = ?,VaiTro = ? WHERE MaNV = ?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV =?";
    String SELECT_ALL_SQL="SELECT * FROM NhanVien";
    String SELETE_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNV =?";
    
    @Override
    public void insert(NhanVien enity) {
        try { 
            jdbcHelper.update(INSERT_SQL, enity.getMaNV(),enity.getMatKhau(),enity.getHoTen(),enity.getVaiTro());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(NhanVien enity) {
        try {
            jdbcHelper.update(UPDATE_SQL, enity.getMatKhau(),enity.getHoTen(),enity.getVaiTro(),enity.getMaNV());
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
    public NhanVien selectById(String id) {
        List<NhanVien> list = this.selectBySql(SELETE_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object...args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try{
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                NhanVien enity = new NhanVien();
                enity.setMaNV(rs.getString("MaNV"));
                enity.setHoTen(rs.getString("HoTen"));
                enity.setMatKhau(rs.getString("MatKhau"));
                enity.setVaiTro(rs.getBoolean("VaiTro"));
            list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            System.out.println("Loi SL by sql");
            throw new RuntimeException(e);
        }
    }
    
}
