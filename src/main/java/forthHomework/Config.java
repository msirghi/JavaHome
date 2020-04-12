package forthHomework;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("config")
public class Config {

  @XStreamAsAttribute
  @XStreamAlias("logdir")
  private String logDir;

  @XStreamAsAttribute
  @XStreamAlias("debugfile")
  private String debugFile;

  @XStreamImplicit
  private List<Server> servers = new ArrayList<>();

  public Config() {
  }

  @Override
  public String toString() {
    return "Config{" +
            "logDir='" + logDir + '\'' +
            ", debugFile='" + debugFile + '\'' +
            ", servers=" + servers +
            '}';
  }

  public Config(String logDir, String debugFile, List<Server> servers) {
    this.logDir = logDir;
    this.debugFile = debugFile;
    this.servers = servers;
  }

  public String getLogDir() {
    return logDir;
  }

  public void setLogDir(String logDir) {
    this.logDir = logDir;
  }

  public String getDebugFile() {
    return debugFile;
  }

  public void setDebugFile(String debugFile) {
    this.debugFile = debugFile;
  }

  public List<Server> getServers() {
    return servers;
  }

  public void setServers(List<Server> servers) {
    this.servers = servers;
  }
}
