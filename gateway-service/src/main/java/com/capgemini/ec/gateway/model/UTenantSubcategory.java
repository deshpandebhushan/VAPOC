
package com.capgemini.ec.gateway.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class UTenantSubcategory {


    private String link;

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
