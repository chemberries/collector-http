/* Copyright 2010-2014 Norconex Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.norconex.collector.http.recrawl;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.norconex.commons.lang.file.ContentType;

/**
 * Previously crawled data.
 * @author Pascal Essiembre
 * @since 2.5.0
 */
public class PreviousCrawlData {

    private String reference;
    private ContentType contentType;
    private Date crawlDate;
    private Long sitemapLastMod;
    private String sitemapChangeFreq;
    private Float sitemapPriority;
    
    public String getReference() {
        return reference;
    }
    public void setReference(String reference) {
        this.reference = reference;
    }
    public ContentType getContentType() {
        return contentType;
    }
    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
    public Date getCrawlDate() {
        return crawlDate;
    }
    public void setCrawlDate(Date crawlDate) {
        this.crawlDate = crawlDate;
    }
    public Long getSitemapLastMod() {
        return sitemapLastMod;
    }
    public void setSitemapLastMod(Long sitemapLastMod) {
        this.sitemapLastMod = sitemapLastMod;
    }
    public String getSitemapChangeFreq() {
        return sitemapChangeFreq;
    }
    public void setSitemapChangeFreq(String sitemapChangeFreq) {
        this.sitemapChangeFreq = sitemapChangeFreq;
    }
    public Float getSitemapPriority() {
        return sitemapPriority;
    }
    public void setSitemapPriority(Float sitemapPriority) {
        this.sitemapPriority = sitemapPriority;
    }
    
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof PreviousCrawlData)) {
            return false;
        }
        PreviousCrawlData castOther = (PreviousCrawlData) other;
        return new EqualsBuilder()
                .append(reference, castOther.reference)
                .append(contentType, castOther.contentType)
                .append(crawlDate, castOther.crawlDate)
                .append(sitemapLastMod, castOther.sitemapLastMod)
                .append(sitemapChangeFreq, castOther.sitemapChangeFreq)
                .append(sitemapPriority, castOther.sitemapPriority)
                .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(reference)
                .append(contentType)
                .append(crawlDate)
                .append(sitemapLastMod)
                .append(sitemapChangeFreq)
                .append(sitemapPriority)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("reference", reference)
                .append("contentType", contentType)
                .append("crawlDate", crawlDate)
                .append("sitemapLastMod", sitemapLastMod)
                .append("sitemapChangeFreq", sitemapChangeFreq)
                .append("sitemapPriority", sitemapPriority)
                .toString();
    }  
}
