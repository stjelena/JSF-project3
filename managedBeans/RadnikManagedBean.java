/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package managedBeans;

import entities.Predlog;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
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

/**
 *
 * @author Jelena
 */
@Named(value = "rmb")
@SessionScoped
public class RadnikManagedBean implements Serializable{
    private List<Predlog> predlozi = new ArrayList<>();
    private String naziv;
    private String tezina;
    private int idP;
    
    public RadnikManagedBean() {
    }
    
    @PostConstruct
    public void initData(){
        Connection conn = DB.getInstance().getConnection();
        try(
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT* FROM predlozi")
                ){
            while(rs.next()){
                Predlog p = new Predlog();
                p.setGlasovi(rs.getInt("glasovi"));
                p.setKomentar(rs.getString("komentar"));
                p.setPosetilac(rs.getString("posetilac"));
                p.setZivotinja(rs.getString("zivotinja"));
                p.setIdP(rs.getInt("idP"));
                predlozi.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RadnikManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            DB.getInstance().putConnection(conn);
        }
    }
    
    public String approve(Predlog p){
        naziv=p.getZivotinja();
        idP=p.getIdP();
        return "dodaj.xhtml?faces-redirect=true";
    }
    
    public String reject(Predlog p){
        Connection conn = DB.getInstance().getConnection();
        try(
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM predlozi WHERE idP=?")
                ){
            stmt.setInt(1, p.getIdP());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RadnikManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DB.getInstance().putConnection(conn);
        }
           return "radnik.xhtml?faces-redirect=true";    
    }
    
    public String add(){
        Connection conn = DB.getInstance().getConnection();
        try(
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO zivotinje(naziv, tezina) VALUES(?,?)");
                PreparedStatement stmt2 =conn.prepareStatement("UPDATE predlozi SET odobren=1 WHERE idP=?")
                ){
        stmt.setString(1, naziv);
        stmt.setInt(2, Integer.parseInt(tezina));
        stmt.execute();
        stmt2.setInt(1, idP);
        stmt2.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RadnikManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DB.getInstance().putConnection(conn);
        }
        return "radnik.xhtml?faces-redirect=true";
    }

    public List<Predlog> getPredlozi() {
        return predlozi;
    }

    public void setPredlozi(List<Predlog> predlozi) {
        this.predlozi = predlozi;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTezina() {
        return tezina;
    }

    public void setTezina(String tezina) {
        this.tezina = tezina;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }
    
    
}
