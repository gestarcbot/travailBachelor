package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import service.Services;

/**
 *
 * @author boris.klett
 */
@Named
@SessionScoped
public class TestBean implements Serializable {
    
    private String testVariable;
    private static final long serialVersionUID = 1L;
    @Inject
    Services services;
    
    public TestBean() {
    }
    
    @PostConstruct
    public void init() {
        testVariable = services.getEventName(1);
    }
    
    public String getTestVariable() {
        return testVariable;
    }
    
    public void setTestVariable(String testVariable) {
        this.testVariable = testVariable;
    }
    
}
