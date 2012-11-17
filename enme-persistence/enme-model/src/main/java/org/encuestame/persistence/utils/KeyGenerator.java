/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: system online surveys Copyright (C) 2011
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.persistence.utils;

/**
 * Interface for generate for unique byte array-based keys.
 * @author Picado, Juan juanATencuestame.org
 * @since Dec 24, 2010 4:01:59 PM
 * @version $Id:$
 */
public interface KeyGenerator {

    /**
     * Get the length, in bytes, of keys created by this generator.
     * Most unique keys are at least 8 bytes in length.
     */
    int getKeyLength();

    /**
     * Generate a new key.
     */
    byte[] generateKey();

}