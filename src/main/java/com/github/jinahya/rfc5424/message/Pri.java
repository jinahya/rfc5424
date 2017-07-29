/*
 * Copyright 2017 Jin Kwon &lt;onacit at gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jinahya.rfc5424.message;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public class Pri {

    public static final int MIN_FACILITY = 0;

    public static final int MAX_FACILITY = 23;

    public static final int MIN_SEVERITY = 0;

    public static final int MAX_SEVERITY = 7;

    public static final int MIN_VALUE = MIN_FACILITY * 8 + MIN_SEVERITY;

    public static final int MAX_VALUE = MAX_FACILITY * 8 + MAX_SEVERITY;

    // -------------------------------------------------------------------------
    @Override
    public String toString() {
        return super.toString() + "{"
               + "facility=" + getFacility()
               + ",severity=" + getSeverity()
               + "}";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.facility;
        hash = 59 * hash + this.severity;
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pri other = (Pri) obj;
        if (this.facility != other.facility) {
            return false;
        }
        if (this.severity != other.severity) {
            return false;
        }
        return true;
    }

    // --------------------------------------------------------------------- val
    @XmlAttribute
    public int getVal() {
        return facility * 8 + severity;
    }

    public void setVal(@Min(MIN_VALUE) @Max(MAX_VALUE) final int val) {
        severity = val % 8;
        facility = (val - severity) / 8;
    }

    // ---------------------------------------------------------------- facility
    public int getFacility() {
        return facility;
    }

    public void setFacility(final int facility) {
        this.facility = facility;
    }

    // ---------------------------------------------------------------- severity
    public int getSeverity() {
        return severity;
    }

    public void setSeverity(final int severity) {
        this.severity = severity;
    }

    // -------------------------------------------------------------------------
    @XmlElement(required = true)
    @Min(MIN_FACILITY)
    @Max(MAX_FACILITY)
    private int facility;

    @XmlElement(required = true)
    @Min(MIN_SEVERITY)
    @Max(MAX_SEVERITY)
    private int severity;
}
