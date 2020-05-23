/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hazelcast.internal.util.phonehome;

import com.hazelcast.test.HazelcastParallelClassRunner;
import com.hazelcast.test.annotation.ParallelJVMTest;
import com.hazelcast.test.annotation.QuickTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(HazelcastParallelClassRunner.class)
@Category({QuickTest.class, ParallelJVMTest.class})
public class PhoneHomeParameterCreatorTest {

    @Test
    public void testPhoneHomeParameterCreator() {
        PhoneHomeParameterCreator phoneHomeParameterCreator = new PhoneHomeParameterCreator();
        phoneHomeParameterCreator.addParam("1", "hazelcast");
        phoneHomeParameterCreator.addParam("2", "phonehome");
        Map<String, String> map = phoneHomeParameterCreator.getParameters();
        assertEquals(phoneHomeParameterCreator.build(), "?1=hazelcast&2=phonehome");
        assertNotNull(map);
        assertEquals(map.get("1"), "hazelcast");
        assertEquals(map.get("2"), "phonehome");
        assertNull(map.get("3"));
    }

    @Test
    public void testEmptyParameter() {
        PhoneHomeParameterCreator phoneHomeParameterCreator = new PhoneHomeParameterCreator();
        Map<String, String> map = phoneHomeParameterCreator.getParameters();
        assertNotNull(map);
        assertEquals(map, new HashMap<>());
        assertEquals(phoneHomeParameterCreator.build(), "?");
    }
}