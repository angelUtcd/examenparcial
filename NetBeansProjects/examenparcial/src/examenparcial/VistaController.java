package examenparcial;
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
public class VistaController implements Initializable {
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("examenparcialPU");
    javax.persistence.EntityManager em =emf.createEntityManager();
     private final ListChangeListener<Personajes> selectortablaPersona = (ListChangeListener.Change<? extends Personajes> c) -> {
        seleccionardate();
    };
     public Personajes persona = new Personajes();
    ObservableList<Personajes>lista=FXCollections.observableArrayList();
    private int posicionPersonajeenTabla;
    @FXML
    private Label label;
    @FXML 
    private TableView<Personajes> tablapersona;
    @FXML private TableColumn<Personajes, String> nombreCL;
    @FXML private TableColumn<Personajes, String> poderCL;
    @FXML private TableColumn<Personajes, String> aliasCL;
    @FXML private TableColumn<Personajes, String> direccionCL;
    @FXML private TableColumn<Personajes, String> telefonoCL;
    @FXML private TableColumn<Personajes, String> correoCL;
    @FXML private TextField nombreTF;
    @FXML private TextField aliasTF;
    @FXML private TextField poderTF;
    @FXML private TextField direccionTF;
    @FXML private TextField telefonoTF;
    @FXML private TextField correoTF;
    @FXML private TextField buscarTF;
    @FXML private Button aniadirBT;
    @FXML private Button modificarBT;
    @FXML private Button eliminarBT;
    @FXML private Button nuevoBT;
    @FXML private Button buscarBT;
    @FXML
    private void aniadir(){
        try {
            em.getTransaction().begin();
            Personajes persona = new Personajes();
            persona.setNombre(nombreTF.getText());
            persona.setAlias(aliasTF.getText());
            persona.setPoder(poderTF.getText());
            persona.setDireccion(direccionTF.getText());
            persona.setTelefono(telefonoTF.getText());
            persona.setCorreo(correoTF.getText());
            em.persist(persona);
            em.getTransaction().commit(); 
         Platform.runLater(() ->{
        this.initiardatos();
        });
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
    }
    public Personajes gettablapersonaSeleccionada(){
        if (tablapersona != null) {
            List<Personajes> tabla =tablapersona.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final Personajes competicionSeleccionada= tabla.get(0);
                return  competicionSeleccionada;
            }
        }
        return null;
    }
    @FXML
    private void modificar(ActionEvent event){
        try {
            em.getTransaction().begin();
        Personajes a = persona;
        System.out.println("nombre: "+ persona.getNombre());
        a.setNombre(nombreTF.getText());
        a.setAlias(aliasTF.getText());
        a.setPoder(poderTF.getText());
        a.setDireccion(direccionTF.getText());
        a.setTelefono(telefonoTF.getText());
        a.setCorreo(correoTF.getText());
        em.merge(a);
        em.getTransaction().commit();
        Platform.runLater(() ->{
        this.initiardatos();
        });
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
    }    
    @FXML
    private void eliminar() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ELIMINAR PERSONAJES");
        alert.setHeaderText("ALERTA, ALERTA, ALERTA");
        alert.setContentText("Estás seguro de eliminar el registro?");

        try {
             Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
            em.getTransaction().begin();
            Personajes p = tablapersona.getSelectionModel().getSelectedItem();
            System.out.println("Se ha seleccionado: " + p.getNombre());
            em.remove(p);
            em.getTransaction().commit();
            Platform.runLater(() -> {
            this.initiardatos();
            });
        } else {
            System.out.println("NO");
        }
        } catch (Exception e) {
            System.out.println("ERROR"+ e);
        }
       
        
    }
    @FXML
    private void nuevo(ActionEvent event){
        try {
            nombreTF.setText("");
        aliasTF.setText("");
        poderTF.setText("");
        direccionTF.setText("");
        telefonoTF.setText("");
        correoTF.setText("");
        modificarBT.setDisable(true);
        eliminarBT.setDisable(true);
        aniadirBT.setDisable(false);
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
    }
    
    
    
    public void inicializartablapersona(){
        tablapersona.setItems(lista);
        nombreCL.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        aliasCL.setCellValueFactory(new  PropertyValueFactory<>("alias"));
        poderCL.setCellValueFactory(new PropertyValueFactory<>("poder"));
        direccionCL.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        telefonoCL.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        correoCL.setCellValueFactory(new PropertyValueFactory<>("correo"));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       this.initiardatos();
        Platform.runLater(() -> {
        nombreTF.requestFocus();
        });
       this.inicializartablapersona();
        modificarBT.setDisable(true);
    }    
    
    public void initiardatos(){
        lista.clear();
        TypedQuery<Personajes> consulta = em.createQuery("SELECT a FROM Personajes a",Personajes.class);
        lista.addAll(consulta.getResultList());
        
    }
    
    public void modificarlista(){
        Personajes persona = tablapersona.getSelectionModel().getSelectedItem();
        System.out.println("Nombre: " + persona.getNombre());
        nombreTF.setText(persona.getNombre());
        modificarBT.setDisable(false);
        eliminarBT.setDisable(false);
    }
    @FXML
    private void seleccionardate(){
         persona = gettablapersonaSeleccionada();
         System.out.println("nombre : ");
        posicionPersonajeenTabla = lista.indexOf(persona);
        if (persona != null) {
            nombreTF.setText(persona.getNombre());
            aliasTF.setText(persona.getAlias());
            poderTF.setText(persona.getPoder());
            direccionTF.setText(persona.getDireccion());
            telefonoTF.setText(persona.getTelefono());
            correoTF.setText(persona.getCorreo());
            modificarBT.setDisable(false);
            eliminarBT.setDisable(false);
            aniadirBT.setDisable(true);
        } 
    }
    public void keyPressed(KeyEvent e){
                if(e.hashCode()==KeyEvent.VK_ENTER){
                    Component contentPane = null;
                    JOptionPane.showMessageDialog(contentPane, "Has pulsado Enter");
                }
                if(e.hashCode()==KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }
            }
    @FXML
    public void nombre(ActionEvent e){
        aliasTF.requestFocus();
    }
    @FXML
    public void alias(){
        poderTF.requestFocus();
    }
    @FXML
    public void poder(){
        direccionTF.requestFocus();
    }
    @FXML
    public void direccion(){
        telefonoTF.requestFocus();
    }
    @FXML
    public void telefono(){
        correoTF.requestFocus();
    }
}
