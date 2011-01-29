/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.shared.ldap.codec;


import org.apache.directory.shared.asn1.Asn1Object;
import org.apache.directory.shared.asn1.DecoderException;
import org.apache.directory.shared.ldap.model.message.Control;


/**
 * Define the transform method to be implemented by all the codec Controls
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public interface ICodecControl<E extends Control> extends Control, IDecorator<E>
{
    /**
     * Decodes raw ASN.1 encoded bytes into an Asn1Object for the control.
     * 
     * @param controlBytes the encoded control bytes
     * @return the decoded Asn1Object for the control
     * @throws DecoderException if anything goes wrong
     */
    Asn1Object decode( byte[] controlBytes ) throws DecoderException;


    /**
     * Checks to see if a value is set for this {@link ICodecControl}.
     */
    boolean hasValue();


    /**
     * Get the control value
     * 
     * @return The control value
     */
    byte[] getValue();


    /**
     * Set the encoded control value
     * 
     * @param value The encoded control value to store
     */
    void setValue( byte[] value );
}