/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cemilan.cemilan_20200140112;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Maulana Alfiansyah
 */
@Controller

public class tablesayuran {
    private int Total(int harga, int jumlah){
        int total = harga * jumlah;
        return total;
    }
    
    private int TotalAkhir(int total){
        int totalAkhir = 0;        
        if (total < 16000){
            totalAkhir = total - (total * 0);
        }
        else if (total > 16000 && total < 25000){
            totalAkhir = total - (total * 10 / 100);
        }
        else if (total > 25000){
            totalAkhir = total - (total * 15 / 100);
        }
           
        return totalAkhir;
    }
    
    private int Diskon(int total){
        int diskon = 0;        
        if (total < 16000){
            diskon = 0;
        }
        else if (total > 16000 && total < 25000){
            diskon = 10;
        }
        else if (total > 25000){
            diskon = 15;
        }
        
        return diskon;
    }
    
   
    
    @RequestMapping("/input")
    //@ResponseBody
    public String getHasil(HttpServletRequest data, Model dataSayur){      
        
        
        String getNama = data.getParameter("nm_sayur");
        int getHarga = Integer.parseInt(data.getParameter("harga"));
        int getJumlah = Integer.parseInt(data.getParameter("jumlah"));
        int gettunai = Integer.parseInt(data.getParameter("bayar"));
        
        
        int total = Total(getHarga, getJumlah);
        int bayar = TotalAkhir(total);
        int diskon = Diskon(total);
        int harga_diskon = total - bayar;
        int tunai = gettunai;
        int kembali = tunai - bayar;
        
        String kembalian = null;        
        if(kembali==0){
            
        }else if(kembali>0){
            kembalian = Integer.toString(kembali);
        }
        
        dataSayur.addAttribute("nama_sayur", getNama);
        dataSayur.addAttribute("harga", getHarga);
        dataSayur.addAttribute("jumlah", getJumlah);
        dataSayur.addAttribute("total", total);
        dataSayur.addAttribute("diskon", diskon);
        dataSayur.addAttribute("harga_diskon", harga_diskon);
        dataSayur.addAttribute("bayar",bayar);
        dataSayur.addAttribute("tunai", tunai);
        dataSayur.addAttribute("kembalian", kembalian);
        
        if(kembali<0){  
            dataSayur.addAttribute("kurang", kembali);
            return "uangkurang";
        }else{            
            return "viewer";
            
        }
    }
}
            

