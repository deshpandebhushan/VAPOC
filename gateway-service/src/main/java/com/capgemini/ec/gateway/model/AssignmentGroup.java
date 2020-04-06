
package com.capgemini.ec.gateway.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssignmentGroup {

    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("value")
    @Expose
    private String value;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("link", link).append("value", value).toString();
    }

}
