package forthHomework;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("server")
public class Server {

  @XStreamAsAttribute
  @XStreamAlias("name")
  private String name;

  @XStreamAsAttribute
  @XStreamAlias("osname")
  private String osname;

  @XStreamAsAttribute
  @XStreamAlias("osversion")
  private String osversion;

  @XStreamImplicit
  private List<String> address = new ArrayList<>();

  public Server() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOsname() {
    return osname;
  }

  public void setOsname(String osname) {
    this.osname = osname;
  }

  public String getOsversion() {
    return osversion;
  }

  public void setOsversion(String osversion) {
    this.osversion = osversion;
  }

  public List<String> getAddress() {
    return address;
  }

  public void setAddress(List<String> address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Server{" +
            "name='" + name + '\'' +
            ", osname='" + osname + '\'' +
            ", osversion='" + osversion + '\'' +
            ", address='" + address + '\'' +
            '}';
  }

  public Server(String name, String osname, String osversion, List<String> address) {
    this.name = name;
    this.osname = osname;
    this.osversion = osversion;
    this.address = address;
  }
}
