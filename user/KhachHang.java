package user;

public class KhachHang {
   private String Id_KhachHang ;
   private String Ten_KhachHang ;
   private String Sdt_KhachHang ;
   private String DiaChi_KhachHang;
   private String Email_KhachHang;
   private String Pass_KhachHang;

   public KhachHang ()
   {
      this.Id_KhachHang = "";
      this.Ten_KhachHang = "";
      this.Sdt_KhachHang = "";
      this.DiaChi_KhachHang = "";
      this.Email_KhachHang = "";
      this.Pass_KhachHang = "";
   }
   public KhachHang(String Id,String Ten, String Sdt, String DiaChi, String Email , String Pass)
   {
      this.Id_KhachHang = Id;
      this.Ten_KhachHang = Ten;
      this.Sdt_KhachHang = Sdt;
      this.DiaChi_KhachHang = DiaChi;
      this.Email_KhachHang = Email;
      this.Pass_KhachHang = Pass;
   }

   // Getter and Setter Id_KhachHang
   public String getId_KhachHang() {
      return Id_KhachHang;
   }

   public void setId_KhachHang(String id_KhachHang) {
      Id_KhachHang = id_KhachHang;
   }
   
   

   // Getter and Setter for Ten_KhachHang
   public String getTen_KhachHang() {
      return Ten_KhachHang;
   }

   public void setTen_KhachHang(String ten_KhachHang) {
      Ten_KhachHang = ten_KhachHang;
   }

   // Getter and Setter for Sdt_KhachHang
   public String getSdt_KhachHang() {
      return Sdt_KhachHang;
   }

   public void setSdt_KhachHang(String sdt_KhachHang) {
      Sdt_KhachHang = sdt_KhachHang;
   }

   // Getter and Setter for DiaChi_KhachHang
   public String getDiaChi_KhachHang() {
      return DiaChi_KhachHang;
   }

   public void setDiaChi_KhachHang(String diaChi_KhachHang) {
      DiaChi_KhachHang = diaChi_KhachHang;
   }

   // Getter and Setter for Email_KhachHang
   public String getEmail_KhachHang() {
      return Email_KhachHang;
   }

   public void setEmail_KhachHang(String email_KhachHang) {
      Email_KhachHang = email_KhachHang;
   }

   // Getter and Setter for Pass_KhachHang
   public String getPass_KhachHang() {
      return Pass_KhachHang;
   }

   public void setPass_KhachHang(String pass_KhachHang) {
      Pass_KhachHang = pass_KhachHang;
   }
}