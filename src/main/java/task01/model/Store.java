package task01.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Store {

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<Category> categories;

    public Store(List<Category> categories) {
        this.categories = categories;
    }

    public Store() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Store ("
                + categories
                + ")";
    }
}
