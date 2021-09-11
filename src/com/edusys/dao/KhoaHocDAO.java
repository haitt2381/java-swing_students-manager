/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.utils.jdbcHelper;
import com.edusys.entity.KhoaHoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author huydu
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, String>{
    String INSERT_SQL = "INSERT INTO KhoaHoc(MaCD, HocPhi,ThoiLuong,NgayKG,GhiChu, MaNV, NgayTao)VALUES(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD = ?,HocPhi = ?,ThoiLuong = ?,NgayKG = ?, GhiChu = ?, MaNV = ?, NgayTao = ? WHERE MaKH = ?";
    String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH =?";
    String SELECT_ALL_SQL="SELECT * FROM KhoaHoc";
    String SELETE_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH =?";

     public List<Integer> selectYear(){
        String sql = "SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC;";
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
    public void insert(KhoaHoc entity) {
        try {
            jdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
        } catch (SQLException ex) {
            Logger.getLogger(KhoaHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(KhoaHoc entity) {
        try {
            jdbcHelper.update(UPDATE_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());
        } catch (SQLException ex) {
            Logger.getLogger(KhoaHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void delete(String id) {
        try {
            jdbcHelper.update(DELETE_SQL, id);
        } catch (SQLException ex) {
            Logger.getLogger(KhoaHocDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public KhoaHoc selectById(String id) {
        List<KhoaHoc> list = this.selectBySql(SELETE_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);  
    }
    
    public List<KhoaHoc> selectByChuyenDe(String macd){
        String sql = "Select * from KhoaHoc where macd = ?";
        return this.selectBySql(sql, macd);
    };
    
    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while(rs.next()){
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getDouble("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
}