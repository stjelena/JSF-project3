/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import entities.Predlog;
import entities.Zivotinja;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DB;
import util.SessionUtils;

/**
 *
 * @author Jelena
 */
@Named(value = "pmb")
@RequestScoped
public class PosetilacManagedBean {
    
    private final List<Zivotinja> zivotinje = new ArrayList<>();
    private final List<Predlog> predlozi=new ArrayList<>();
    private String zivotinja;
    private String komentar;
    
    public PosetilacManagedBean() {
    }
    
    @PostConstruct
    public void initData(){
         DB inst = DB.getInstance();
        Connection db = inst.getConnection();
        try (
            Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM zivotinje");
            Statement stmt2 = db.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM predlozi WHERE odobren = 0");
            PreparedStatement stmt3=db.prepareStatement("SELECT* FROM glasovi WHERE posetilac=? AND idP=?")
        ) {
            while (rs.next()) {
                Zivotinja z = new Zivotinja();
                z.setNaziv(rs.getString("naziv"));
                z.setTezina(rs.getInt("tezina"));
                z.setIdZ(rs.getInt("idZ"));
                zivotinje.add(z);
            }
            while (rs2.next()) {
                Predlog p = new Predlog();
                p.setGlasovi(rs2.getInt("glasovi"));
                p.setKomentar(rs2.getString("komentar"));
                p.setPosetilac(rs2.getString("posetilac"));
                p.setZivotinja(rs2.getString("zivotinja"));
                int idP = rs2.getInt("idP");
                p.setIdP(idP);
                
                stmt3.setString(1, SessionUtils.getAttribute("kor_ime").toString());
                stmt3.setInt(2, idP);
                
                try(ResultSet rs3=stmt3.executeQuery()){
                    p.setGlasao(rs3.next());
                }
              
                predlozi.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PosetilacManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            inst.putConnection(db);
        }
        zivotinje.sort((a, b) -> a.getTezina() - b.getTezina());
        
        
    }
    
    public String suggest(){
        Connection conn=DB.getInstance().getConnection();
        try(PreparedStatement stmt=conn.prepareStatement("INSERT INTO predlozi(posetilac, zivotinja, komentar, glasovi, odobren) VALUES(?,?,?,0,0)")){
            stmt.setString(1, SessionUtils.getAttribute("kor_ime").toString());
            stmt.setString(2, zivotinja);
            stmt.setString(3, komentar);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PosetilacManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DB.getInstance().putConnection(conn);
        }
        return "posetilac.xhtml?faces-redirect=true";
    }
    
    public String vote(Predlog p){
        Connection conn = DB.getInstance().getConnection();
        try(
                PreparedStatement stmt=conn.prepareStatement("INSERT INTO glasovi(idP, posetilac) VALUES(?,?)");
                PreparedStatement stmt2=conn.prepareStatement("UPDATE predlozi SET glasovi=glasovi+1 WHERE idP=?")
                ){
            
        stmt.setInt(1, p.getIdP());
        stmt.setString(2, SessionUtils.getAttribute("kor_ime").toString());
        stmt.execute();
        stmt2.setInt(1, p.getIdP());
        stmt2.execute();
        
    }   catch (SQLException ex) {
            Logger.getLogger(PosetilacManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
           DB.getInstance().putConnection(conn);
        }
        return "posetilac.xhtml?faces-redirect=true";
    }
    public List<Zivotinja> getZivotinje() {
        return zivotinje;
    }

   

    public List<Predlog> getPredlozi() {
        return predlozi;
    }

    public String getZivotinja() {
        return zivotinja;
    }

    public void setZivotinja(String zivotinja) {
        this.zivotinja = zivotinja;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

  
}
