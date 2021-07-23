package org.vaccs.eevis.util;

import com.jpro.webapi.WebAPI;
import javafx.scene.control.Label;

public class FileHandler extends Label {
    public WebAPI.FileUploader fileHandler = null;

    public FileHandler (WebAPI webAPI) {

        fileHandler = webAPI.makeFileUploadNode(this);

    }

    public FileHandler(WebAPI webAPI, String text, boolean drop) {
        setText(text);
        getStyleClass().add("file-handler");
        setWrapText(true);

        fileHandler = webAPI.makeFileUploadNode(this);

        fileHandler.setSelectFileOnClick(true);
        if (drop) {
            fileHandler.setSelectFileOnDrop(true);
            fileHandler.fileDragOverProperty().addListener((o, oldV, newV) -> {
                if (newV) {
                    getStyleClass().add("file-drag");
                } else {
                    if (getStyleClass().contains("file-drag")) {
                        getStyleClass().remove("file-drag");
                    }
                }
            });
        }
        fileHandler.setOnFileSelected((file) -> {
            updateText();
            fileHandler.uploadFile();
        });

        fileHandler.progressProperty().addListener((obs, oldV, newV) -> {
            updateText();
        });
    }

    private void updateText() {
        String percentages = "";
        percentages = (int) (fileHandler.getProgress() * 100) + "%";
        setText(fileHandler.selectedFileProperty() + percentages);
    }

}
