package controller;

import model.ParentViewModel;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public interface Controller {
    // controller interface: relevant for saving a general controller object in SaveScene

    // set the parent model of a controller
    void setParentViewModel(ParentViewModel parentViewModel) throws InterruptedException;
}
