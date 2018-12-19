package bzu.mobile.project.dbModels;

public class CartItem {
    private Integer imgUrl;
    private String price;

    public CartItem() {}

    public CartItem(Integer imgUrl, String price) {
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public Integer getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
