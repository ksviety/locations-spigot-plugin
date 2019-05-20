package me.ksviety.plugins.mc.locations.util.NMS.objects;

import com.google.gson.Gson;

public class Text implements ISerializable {

    private String text = "";
    private String color = Colors.WHITE;
    private boolean bold = false;
    private boolean italic = false;
    private boolean underline = false;

    public Text setText(String text) {
        this.text = text;
        return this;
    }

    public Text setColor(String color) {
        this.color = color;
        return this;
    }

    public Text setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public Text setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public Text setUnderline(boolean underline) {
        this.underline = underline;
        return this;
    }

    @Override
    public String toJson() {
        return new Gson().toJson(this);
    }

}
