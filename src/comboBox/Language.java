package comboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Language {
    private String langName;
    private static ObservableList<Language> languages = FXCollections.observableArrayList();

    public Language(String langName) {
        this.langName = langName;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    private static void setLanguages() {
        if(languages.size() == 0) {
            languages.add(new Language("English"));
            languages.add(new Language("Français"));
        }
    }

    public static ObservableList<Language> getLanguages() {
        return languages;
    }

    public String toString() {
        return (langName);
    }
}
