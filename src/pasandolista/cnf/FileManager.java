package pasandolista.cnf;

public class FileManager {
     
    protected String filepath = "cnf/config.xml";
    protected static final String originalFilepath = "cnf/config.xml";

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    
    public void resetFilepath(){
        filepath = originalFilepath;
    }

}
