package task01.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper(name = "subcategories")
    @XmlElement(name = "subcategory")
    private List<SubCategory> subCategories;

    public Category(String name, List<SubCategory> subCategories) {
        this.name = name;
        this.subCategories = subCategories;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @Override
    public String toString() {
        return "\ncategory:"
                + name
                + " \n"
                + subCategories;
    }
}
