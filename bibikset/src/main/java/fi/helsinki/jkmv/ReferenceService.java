package fi.helsinki.jkmv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.config.ServerConfig;


@Service
public class ReferenceService {
	private EbeanServer ormServer;
	
	/* *******************************************************************
	* Constructor/setup
	*********************************************************************/
	
	public ReferenceService() {
		this(true, true);
	}
	
	public ReferenceService(boolean inMemory) {
		this(inMemory, true);
	}
	
	public ReferenceService(boolean inMemory, boolean dropAndCreate) {
		DataSourceConfig dsConf = initDataSource(inMemory, "~/bibikset");
		ormServer = initDB(dsConf, dropAndCreate);
	}
	
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
	
	public List<Reference> findAllRefs() {
		return findAllRefs(false);
	}
	
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
	
	public void trashRef(int id) {
		Reference ref = findById(id);
		if(ref != null) {
			ref.setInTrash(true);
			ormServer.save(ref);
		}
	}
	
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
	
	public String renderBibtex() {
		BibtexIO bio = new BibtexIO(findAllRefs());
		return bio.renderToBibtex();
	}
}