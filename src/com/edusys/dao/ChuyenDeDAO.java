package com.edusys.dao;

import com.edusys.utils.jdbcHelper;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    String INSERT_SQL = "INSERT INTO ChuyenDe(MaCD, TenCD, HocPhi,ThoiLuong,Hinh,Mota)VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?,HocPhi = ?,ThoiLuong = ?,Hinh = ?, Mota = ? WHERE MaCD = ?";
    String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD =?";
    String SELECT_ALL_SQL="SELECT *FROM ChuyenDe";
    String SELETE_BY_ID_SQL = "SELECT * FROM ChuyenDE WHERE MaCD =?";

    @Override
    public void insert(ChuyenDe enity) {
        try { 
            jdbcHelper.update(INSERT_SQL, enity.getMaCD(),enity.getTenCD(),enity.getHocPhi(),enity.getThoiLuong(),enity.getHinh(),enity.getMoTa());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(ChuyenDe enity) {
        try {
            jdbcHelper.update(UPDATE_SQL, enity.getTenCD(),enity.getHocPhi(),enity.getThoiLuong(),enity.getHinh(), enity.getMoTa(),enity.getMaCD());
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }}

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = this.selectBySql(SELETE_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectAll() {
         return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<ChuyenDe>();
        try{
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                ChuyenDe enity = new ChuyenDe();
                enity.setMaCD(rs.getString("MaCD"));
                enity.setTenCD(rs.getString("TenCD"));
                enity.setHocPhi(rs.getFloat("HocPhi"));
                enity.setThoiLuong(rs.getInt("ThoiLuong"));
                enity.setHinh(rs.getString("Hinh"));
                enity.setMoTa(rs.getString("mota"));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
