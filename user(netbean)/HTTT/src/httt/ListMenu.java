
package httt;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class ListMenu<E extends Object> extends JList<E>{

    private final DefaultListModel model;
    private int selectedIndex = -1;
    public ListMenu() {
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    int Index = locationToIndex(e.getPoint());
                    Object o = model.getElementAt(Index);
                    if(o instanceof Model_menu){
                        Model_menu menu = (Model_menu)o;
                        if(menu.getType() == Model_menu.Menu_Type.TITLE){
                            selectedIndex = Index;
                        }
                    }else{
                        selectedIndex = Index;
                    }
                    repaint();
                }
            }
            
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Model_menu data;
                if(value instanceof Model_menu){
                    data = (Model_menu)value;
                }else{
                    data = new Model_menu("",value+"", Model_menu.Menu_Type.EMPTY);
                }
                MenuItem item = new MenuItem(data);
                item.setSelected(selectedIndex == index);
                return item;
            }
        };
    }
    
    public void addItem(Model_menu data){
        model.addElement(data);
    }
    
    
}
