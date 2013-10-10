package fi.helsinki.jkmv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;

/** 
* Service for handling list of Reference objects with persistency.
*/
@Service
public class ReferenceService {
	private EbeanServer ormServer;
        private Validate validateObj;
	
	/* *******************************************************************
	* Constructor/setup
	*********************************************************************/
	
	/** 
	* Creates new reference service and DB in memory
	* @see #ReferenceService(boolean, boolean)
	*/
	public ReferenceService() {
		this(true, true);
	}
	
	/** 
	* Creates new reference service and initialize database
	* @param inMemory whether to create the database in memory or disk
	* @param dropAndCreate whether to initialize database 
	*/
	public ReferenceService(boolean inMemory, boolean dropAndCreate) {
		DataSourceConfig dsConf = initDataSource(inMemory, "~/bibikset");
		ormServer = initDB(dsConf, dropAndCreate);
                validateObj = new Validate();
	}
	
	/** 
	* Create ebean ORM server object with the provided DataSourceConfig
	*/
	private EbeanServer initDB(DataSourceConfig dsConf, boolean dropAndCreate) {
		ServerConfig config = new ServerConfig();
		config.setName("refefenceDb");
		config.setDataSourceConfig(dsConf);
		config.setDefaultServer(false);
		config.setRegister(false);
		
		config.addClass(Reference.class);
		
		if(dropAndCreate) {
			config.setDdlGenerate(true);
			config.setDdlRun(true);
		}        
		
		return EbeanServerFactory.create(config);
	}
	
	/** 
	* Create datasource config 
	*/
	private DataSourceConfig initDataSource(boolean inMemory, String dbPath) {
		DataSourceConfig dsconf = new DataSourceConfig();
		dsconf.setDriver("org.h2.Driver");
		dsconf.setUsername("test");
		dsconf.setPassword("test");
		// delay set to -1 to avoid deletion of the database on disconnect
		if(inMemory)
			dsconf.setUrl("jdbc:h2:mem:references;DB_CLOSE_DELAY=-1"); 
		else
			dsconf.setUrl("jdbc:h2:" + dbPath + ";");
		
		dsconf.setHeartbeatSql("select 1 ");
		return dsconf;
	}
	
	/* *******************************************************************
	* Finders
	*********************************************************************/
	
        /** 
	* Validation for reference object
	*/
        public boolean isValidReference(Reference validateRef){
            if (validateObj.validateEntry(validateRef)){
                return true;
            }
            return false;
        }
        
        /** 
	* Return all entry type names
	*/
	public String[] getTypeNames() {
            return validateObj.getTypeNames();
//                Reference ref = new Reference();
//                return ref.getTypeNames();
	}

        /*
         * Retrieve all key values for validation purposes
         */
        public List<String> getKeys() {
		List<Reference> refs = ormServer.find(Reference.class).findList();
                List<String> keys = new ArrayList<String>();
                for (Reference ref : refs){
                    keys.add(ref.getKey());
                }
                return keys;
	}
        
	/** 
	* All references not in trash
	*/
	public List<Reference> findAllRefs() {
		return findAllRefs(false);
	}

               
	/** 
	* All references not in trash or in trash depending on given parameter
	*/
	public List<Reference> findAllRefs(boolean inTrash) {
		List<Reference> refs = ormServer.find(Reference.class)
		.where()
		.eq("inTrash", inTrash)
		.orderBy("key desc")
		.findList();
		if(refs == null)
			return new ArrayList<Reference>();
		else	
			return refs;
	}
	
	public Reference findByKey(String key) {
		return ormServer.find(Reference.class)
			.where()
			.eq("key", key)
			.findUnique();
	}
	
	public Reference findById(int id) {
		return ormServer.find(Reference.class)
			.where()
			.eq("id", id)
			.findUnique();
	}
	
	
	/* *******************************************************************
	* Modify references
	*********************************************************************/
	
	public void addRef(Reference reference) {
		ormServer.save(reference);
	}
	
	/** 
	* Move reference to trash
	*/
	public void trashRef(int id) {
		trashRefImpl(id, true);
	}
	
	/** 
	* Ressurect reference from trash
	*/
	public void unTrashRef(int id) {
		trashRefImpl(id, false);
	}
	
	private void trashRefImpl(int id, boolean setTrashTo) {
		Reference ref = findById(id);
		if(ref != null) {
			ref.setInTrash(setTrashTo);
			ormServer.save(ref);
		}
	}
	
	/** 
	* Permanently delete all references currently in trash
	*/
	public void emptyTrash() {
		List<Reference> refs = ormServer.find(Reference.class)
			.where()
			.eq("inTrash", true)
			.orderBy("key desc")
			.findList();
		if(refs == null)
			return ;
		
		for(Reference ref : refs) 
			ormServer.delete(ref);
	}
	
	
	/* *******************************************************************
	* Other services
	*********************************************************************/
	
	/** 
	* Return bibtex database containing all references not in trash
	*/
	public String renderBibtex() {
		BibtexIO bio = new BibtexIO(findAllRefs());
		return bio.renderToBibtex();
	}
        
}