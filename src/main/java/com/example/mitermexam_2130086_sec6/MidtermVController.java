package com.example.mitermexam_2130086_sec6;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MidtermVController
{
    @javafx.fxml.FXML
    private TextField perUnitProductCostTextField;
    @javafx.fxml.FXML
    private Label predefinedVatLabel;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Float> summaryUnitPriceTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Float> summaryTotalAmountTableColumn;
    @javafx.fxml.FXML
    private TableView<CartItem> cartDetailTableView;
    @javafx.fxml.FXML
    private Label showTotalVatAmountCriteriaLabel;
    @javafx.fxml.FXML
    private Label UnitPriceLabel;
    @javafx.fxml.FXML
    private TableColumn<CartItem, String> summaryProductTableColumn;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Float> summaryVatAmountTableColumn;
    @javafx.fxml.FXML
    private ComboBox<Integer> vatToConsiderComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> productListCombobox;
    @javafx.fxml.FXML
    private ComboBox<Integer> quantityComboBox;
    @javafx.fxml.FXML
    private TableColumn<CartItem, Integer> summaryQuantityTableColumn;


    private ArrayList<CartItem> cartList = new ArrayList<>();

    private Map<String, ProductInfo> productInfoMap = new HashMap<>();


    private static class ProductInfo {
        float unitPrice;
        int vatRate;

        ProductInfo(float unitPrice, int vatRate){
            this.unitPrice = unitPrice;
            this.vatRate = vatRate;
        }
    }


    @FXML
    public void initialize() {
        quantityComboBox.getItems().addAll(1,2, 3, 4, 5, 6, 7, 8, 9, 10);
        vatToConsiderComboBox.getItems().addAll(1,2, 3, 4, 5, 6, 7, 8, 9, 10);

        productInfoMap.put("Arong Dairy Milk", new ProductInfo(50, 1));

        productListCombobox.getItems().addAll(productInfoMap.keySet());

        productListCombobox.setOnAction(this::onProductSelected);

        summaryProductTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        summaryUnitPriceTableColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getUnitPrice()).asObject());
        summaryQuantityTableColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject());
        summaryTotalAmountTableColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getTotalAmount()).asObject());
        summaryVatAmountTableColumn.setCellValueFactory(cell -> new SimpleFloatProperty(cell.getValue().getVatRate()).asObject());

    }

    @javafx.fxml.FXML
    public void showTotalAmountCriteriaButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void checkOutButton(ActionEvent actionEvent) {
        cartDetailTableView.getItems().clear();
        cartDetailTableView.getItems().addAll(cartList);

    }

    @javafx.fxml.FXML
    public void addProductCartButton(ActionEvent actionEvent) {
        String selectedProduct = productListCombobox.getValue();
        ProductInfo info = productInfoMap.get(selectedProduct);
        int quantity = quantityComboBox.getValue();

        CartItem existingItem = null;
        for (CartItem item : cartList) {
            if (item.getProductName().equals(selectedProduct)) {
                existingItem = item;
                break;
            }
        }
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }
        else{
            CartItem newItem = new CartItem(selectedProduct, info.unitPrice,quantity,info.vatRate);
            cartList.add(newItem);
        }



    }

    @javafx.fxml.FXML
    public void onProductSelected(ActionEvent actionEvent) {
        String selectedProduct = productListCombobox.getValue();
        ProductInfo info = productInfoMap.get(selectedProduct);
        if (info != null) {
            UnitPriceLabel.setText(String.valueOf(info.unitPrice));
            predefinedVatLabel.setText(String.valueOf(info.vatRate));
        }
    }


}