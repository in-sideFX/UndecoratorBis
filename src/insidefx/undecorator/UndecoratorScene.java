/*
 * Copyright 2014-2016 Arnaud Nouard. All rights reserved.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package insidefx.undecorator;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author In-SideFX
 */
public class UndecoratorScene extends Scene {

    static public final String DEFAULT_STYLESHEET = "skin/undecorator.css";
    static public final String DEFAULT_STYLESHEET_UTILITY = "skin/undecoratorUtilityStage.css";
    static public final String DEFAULT_STAGEDECORATION = "stagedecoration.fxml";
    static public final String DEFAULT_STAGEDECORATION_UTILITY = "stageUtilityDecoration.fxml";

    static public final String DEFAULT_STYLESHEET_TOUCH = "skin/Touch/undecorator.css";
    static public final String DEFAULT_STYLESHEET_UTILITY_TOUCH = "skin/Touch/undecoratorUtilityStage.css";
    static public final String DEFAULT_STAGEDECORATION_TOUCH = "stagedecorationTouch.fxml";
    static public final String DEFAULT_STAGEDECORATION_UTILITY_TOUCH = "stageUtilityDecorationTouch.fxml";

    static public String STYLESHEET = DEFAULT_STYLESHEET_TOUCH;
    static public String STYLESHEET_UTILITY = DEFAULT_STYLESHEET_UTILITY_TOUCH;
    static public String STAGEDECORATION = DEFAULT_STAGEDECORATION_TOUCH;
    static public String STAGEDECORATION_UTILITY = DEFAULT_STAGEDECORATION_UTILITY_TOUCH;

    Undecorator undecorator;

    static public void setClassicDecoration() {
        UndecoratorScene.STAGEDECORATION = UndecoratorScene.DEFAULT_STAGEDECORATION;
        UndecoratorScene.STAGEDECORATION_UTILITY = UndecoratorScene.DEFAULT_STAGEDECORATION_UTILITY;
        UndecoratorScene.STYLESHEET = UndecoratorScene.DEFAULT_STYLESHEET;
        UndecoratorScene.STYLESHEET_UTILITY = UndecoratorScene.DEFAULT_STYLESHEET_UTILITY;
    }

    /**
     * Basic constructor with built-in behavior
     *
     * @param stage The main stage
     * @param root your UI to be displayed in the Stage
     */
    public UndecoratorScene(Stage stage, Region root) {
        this(stage, StageStyle.TRANSPARENT, root, STAGEDECORATION);
    }

    /**
     * UndecoratorScene constructor
     *
     * @param stage The main stage
     * @param stageStyle could be StageStyle.UTILITY or StageStyle.TRANSPARENT
     * @param root your UI to be displayed in the Stage
     * @param stageDecorationFxml Your own Stage decoration or null to use the built-in one
     */
    public UndecoratorScene(Stage stage, StageStyle stageStyle, Region root, String stageDecorationFxml) {

        super(root);

        /*
         * Fxml
         */
        if (stageDecorationFxml == null) {
            if (stageStyle == StageStyle.UTILITY) {
                stageDecorationFxml = STAGEDECORATION_UTILITY;
            } else {
                stageDecorationFxml = STAGEDECORATION;
            }
        }
        undecorator = new Undecorator(stage, root, stageDecorationFxml, stageStyle);
        super.setRoot(undecorator);

        // Customize it by CSS if needed:
        if (stageStyle == StageStyle.UTILITY) {
            undecorator.getStylesheets().add(STYLESHEET_UTILITY);
        } else {
            undecorator.getStylesheets().add(STYLESHEET);
        }

        // Transparent scene and stage
        if (stage.getStyle() != StageStyle.TRANSPARENT) {
            stage.initStyle(StageStyle.TRANSPARENT);
        }
        super.setFill(Color.TRANSPARENT);

        // Default Accelerators
        undecorator.installAccelerators(this);

        // Forward pref and max size to main stage
        stage.setMinWidth(undecorator.getMinWidth());
        stage.setMinHeight(undecorator.getMinHeight());
        stage.setWidth(undecorator.getPrefWidth());
        stage.setHeight(undecorator.getPrefHeight());
    }

    public void removeDefaultStylesheet() {
        undecorator.getStylesheets().remove(STYLESHEET);
        undecorator.getStylesheets().remove(STYLESHEET_UTILITY);
    }

    public void addStylesheet(String css) {
        undecorator.getStylesheets().add(css);
    }

    public void setAsStageDraggable(Stage stage, Node node) {
        undecorator.setAsStageDraggable(stage, node);
    }

    public void setBackgroundStyle(String style) {
        undecorator.getShadowNode().setStyle(style);
    }

    public void setBackgroundOpacity(double opacity) {
        undecorator.getShadowNode().setOpacity(opacity);
    }

    public void setBackgroundPaint(Paint paint) {
        undecorator.removeDefaultBackgroundStyleClass();
        undecorator.getShadowNode().setFill(paint);
    }

    public Undecorator getUndecorator() {
        return undecorator;
    }

    public void setFadeInTransition() {
        undecorator.setFadeInTransition();
    }

    public void setFadeOutTransition() {
        undecorator.setFadeOutTransition();
    }

}
