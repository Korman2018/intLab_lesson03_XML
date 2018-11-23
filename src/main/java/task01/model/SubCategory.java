package task01.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class SubCategory {
    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products;

    public SubCategory(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public SubCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "subCategory:"
                + name
                + " "
                + products
                + "\n";
    }
}
