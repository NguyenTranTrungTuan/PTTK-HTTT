
package httt;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_menu {
    private String Icon;
    private String Name;
    private Menu_Type type;

    public Model_menu() {
    }

    public Model_menu(String Icon, String Name, Menu_Type type) {
        this.Icon = Icon;
        this.Name = Name;
        this.type = type;
    }
    
    
    /**
     * @return the Icon
     */
    public String getIcon() {
        return Icon;
    }

    /**
     * @param Icon the Icon to set
     */
    public void setIcon(String Icon) {
        this.Icon = Icon;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the type
     */
    public Menu_Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Menu_Type type) {
        this.type = type;
    }
    
    public Icon toIcon(){
        return new ImageIcon(getClass().getResource("/Icon/" +Icon+".png"));
    }
    
    
    public static enum Menu_Type{
        TITLE, MENU, EMPTY
    }
}
