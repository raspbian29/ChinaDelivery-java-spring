package com.chinabox.delivery.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "package_request")
public class PackageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private LocalDateTime createdDate;
    @Column
    private LocalDateTime chinaWarehouseArrivedDate;
    @Column
    private LocalDateTime chinaWarehouseSentDate;
    @Column
    private LocalDateTime localWarehouseArrivedDate;
    @Column
    private LocalDateTime PackageRequestClose;
    @Column
    private ItemType itemType;
    @Column
    private BigDecimal price;
    @Column
    private Currency currency;
    @Column
    private Double amount;
    @Column
    private String shopName;
    @Column
    private String itemURL;
    @Column
    private Boolean itemPhoto;
    @Column
    private Boolean itemInsurance;
    @Column
    private Boolean itemCheck;
    @Column
    private Boolean itemRepack;
    @Column
    private Boolean itemSplit;
    @ManyToOne
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getChinaWarehouseArrivedDate() {
        return chinaWarehouseArrivedDate;
    }

    public void setChinaWarehouseArrivedDate(LocalDateTime chinaWarehouseArrivedDate) {
        this.chinaWarehouseArrivedDate = chinaWarehouseArrivedDate;
    }

    public LocalDateTime getChinaWarehouseSentDate() {
        return chinaWarehouseSentDate;
    }

    public void setChinaWarehouseSentDate(LocalDateTime chinaWarehouseSentDate) {
        this.chinaWarehouseSentDate = chinaWarehouseSentDate;
    }

    public LocalDateTime getLocalWarehouseArrivedDate() {
        return localWarehouseArrivedDate;
    }

    public void setLocalWarehouseArrivedDate(LocalDateTime localWarehouseArrivedDate) {
        this.localWarehouseArrivedDate = localWarehouseArrivedDate;
    }

    public LocalDateTime getPackageRequestClose() {
        return PackageRequestClose;
    }

    public void setPackageRequestClose(LocalDateTime packageRequestClose) {
        PackageRequestClose = packageRequestClose;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public Boolean getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(Boolean itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public Boolean getItemInsurance() {
        return itemInsurance;
    }

    public void setItemInsurance(Boolean itemInsurance) {
        this.itemInsurance = itemInsurance;
    }

    public Boolean getItemCheck() {
        return itemCheck;
    }

    public void setItemCheck(Boolean itemCheck) {
        this.itemCheck = itemCheck;
    }

    public Boolean getItemRepack() {
        return itemRepack;
    }

    public void setItemRepack(Boolean itemRepack) {
        this.itemRepack = itemRepack;
    }

    public Boolean getItemSplit() {
        return itemSplit;
    }

    public void setItemSplit(Boolean itemSplit) {
        this.itemSplit = itemSplit;
    }

}
