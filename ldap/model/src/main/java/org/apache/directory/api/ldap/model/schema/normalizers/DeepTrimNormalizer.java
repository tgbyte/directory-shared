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
package org.apache.directory.api.ldap.model.schema.normalizers;


import java.io.IOException;

import org.apache.directory.api.i18n.I18n;
import org.apache.directory.api.ldap.model.entry.StringValue;
import org.apache.directory.api.ldap.model.entry.Value;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.exception.LdapInvalidAttributeValueException;
import org.apache.directory.api.ldap.model.message.ResultCodeEnum;
import org.apache.directory.api.ldap.model.schema.Normalizer;
import org.apache.directory.api.ldap.model.schema.PrepareString;


/**
 * Normalizer which trims down whitespace replacing multiple whitespace
 * characters on the edges and within the string with a single space character
 * thereby preserving tokenization order.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
@SuppressWarnings("serial")
public class DeepTrimNormalizer extends Normalizer
{
    /**
     * Creates a new instance of DeepTrimNormalizer with OID known.
     * 
     * @param oid The MR OID to use with this Normalizer
     */
    public DeepTrimNormalizer( String oid )
    {
        super( oid );
    }


    /**
     * Creates a new instance of DeepTrimNormalizer when the Normalizer must be
     * instantiated before setting the OID.
     */
    public DeepTrimNormalizer()
    {
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Value<?> normalize( Value<?> value ) throws LdapException
    {
        try
        {
            String normalized = PrepareString.normalize( value.getString(),
                PrepareString.StringType.DIRECTORY_STRING );

            return new StringValue( normalized );
        }
        catch ( IOException ioe )
        {
            throw new LdapInvalidAttributeValueException( ResultCodeEnum.INVALID_ATTRIBUTE_SYNTAX, I18n.err(
                I18n.ERR_04224, value ), ioe );
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String normalize( String value ) throws LdapException
    {
        try
        {
            return PrepareString.normalize( value, PrepareString.StringType.DIRECTORY_STRING );
        }
        catch ( IOException ioe )
        {
            throw new LdapInvalidAttributeValueException( ResultCodeEnum.INVALID_ATTRIBUTE_SYNTAX, I18n.err(
                I18n.ERR_04224, value ), ioe );
        }
    }
}