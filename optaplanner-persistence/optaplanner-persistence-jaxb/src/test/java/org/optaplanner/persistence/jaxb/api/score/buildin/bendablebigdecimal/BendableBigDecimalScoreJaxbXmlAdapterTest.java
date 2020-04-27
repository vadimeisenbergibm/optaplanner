/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

package org.optaplanner.persistence.jaxb.api.score.buildin.bendablebigdecimal;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.junit.jupiter.api.Test;
import org.optaplanner.core.api.score.buildin.bendablebigdecimal.BendableBigDecimalScore;
import org.optaplanner.persistence.jaxb.api.score.AbstractScoreJaxbXmlAdapterTest;

public class BendableBigDecimalScoreJaxbXmlAdapterTest extends AbstractScoreJaxbXmlAdapterTest {

    @Test
    public void serializeAndDeserialize() {
        assertSerializeAndDeserialize(null, new TestBendableBigDecimalScoreWrapper(null));

        BendableBigDecimalScore score = BendableBigDecimalScore.of(
                new BigDecimal[]{new BigDecimal("1000.0001"), new BigDecimal("200.0020")}, new BigDecimal[]{new BigDecimal("34.4300")});
        assertSerializeAndDeserialize(score, new TestBendableBigDecimalScoreWrapper(score));

        score = BendableBigDecimalScore.ofUninitialized(-7,
                new BigDecimal[]{new BigDecimal("1000.0001"), new BigDecimal("200.0020")}, new BigDecimal[]{new BigDecimal("34.4300")});
        assertSerializeAndDeserialize(score, new TestBendableBigDecimalScoreWrapper(score));
    }

    @XmlRootElement
    public static class TestBendableBigDecimalScoreWrapper extends TestScoreWrapper<BendableBigDecimalScore> {

        @XmlJavaTypeAdapter(BendableBigDecimalScoreJaxbXmlAdapter.class)
        private BendableBigDecimalScore score;

        @SuppressWarnings("unused")
        private TestBendableBigDecimalScoreWrapper() {
        }

        public TestBendableBigDecimalScoreWrapper(BendableBigDecimalScore score) {
            this.score = score;
        }

        @Override
        public BendableBigDecimalScore getScore() {
            return score;
        }

    }

}
