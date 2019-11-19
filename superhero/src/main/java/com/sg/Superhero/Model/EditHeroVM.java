/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.Superhero.Model;

import java.util.List;

/**
 *
 * @author cas
 */
public class EditHeroVM {
     private List<Integer> selectedId;
     private Hero editedHero;

    /**
     * @return the selectedId
     */
    public List<Integer> getSelectedId() {
        return selectedId;
    }

    /**
     * @param selectedId the selectedId to set
     */
    public void setSelectedId(List<Integer> selectedId) {
        this.selectedId = selectedId;
    }

    /**
     * @return the editedHero
     */
    public Hero getEditedHero() {
        return editedHero;
    }

    /**
     * @param editedHero the editedHero to set
     */
    public void setEditedHero(Hero editedHero) {
        this.editedHero = editedHero;
    }
}
