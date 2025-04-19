package Admin_quanlitaikhoan.DTO;

public class KhachHang {
        private String makh;
        private String tenkh;
        private String sdt;
        private String email;
        private String diachikh;
        private String passwordkh;

        public KhachHang(String makh, String tenkh, String sdt, String email, String diachikh, String passwordkh) {
            this.makh = makh;
            this.tenkh = tenkh;
            this.sdt = sdt;
            this.email = email;
            this.diachikh = diachikh;
            this.passwordkh = passwordkh;
        }
        public String getMakh() {
            return makh;
        }
        public void setMakh(String makh) {
            this.makh = makh;
        }
        public String getTenkh() {
            return tenkh;
        }
        public void setTenkh(String tenkh) {
            this.tenkh = tenkh;
        }
        public String getSdt() {
            return sdt;
        }
        public void setSdt(String sdt) {
            this.sdt = sdt;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getDiachikh() {
            return diachikh;
        }
        public void setDiachikh(String diachikh) {
            this.diachikh = diachikh;
        }
        public String getPasswordkh() {
            return passwordkh;
        }
        public void setPasswordkh(String passwordkh) {
            this.passwordkh = passwordkh;
        }
        @Override
        public String toString() {
            return "KhachHang{" +
                    "makh='" + makh + '\'' +
                    ", tenkh='" + tenkh + '\'' +
                    ", sdt='" + sdt + '\'' +
                    ", email='" + email + '\'' +
                    ", diachikh='" + diachikh + '\'' +
                    ", passwordkh='" + passwordkh + '\'' +
                    '}';
        }
        

}
