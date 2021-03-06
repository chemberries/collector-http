/* Copyright 2015-2019 Norconex Inc.
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
package com.norconex.collector.http.crawler;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class URLCrawlScopeStrategyTest {

    @Test
    public void testURLCrawlScopeStrategy() throws IOException {
        String url = "http://example.com/base/for/test.html";
        String diffProtocol = "https://example.com/diff/protocol.html";
        String diffDomain = "http://www.example.com/diff/domain.html";
        String diffPort = "http://example.com:81/diff/port.html";
        String sameSite = "http://example.com:80/diff/same.html";
        String noSchemeDiffDomain = "//server1.elsewhere.com";

        URLCrawlScopeStrategy s = null;

        // No scope defined
        s = new URLCrawlScopeStrategy();
        Assert.assertTrue(s.isInScope(url, diffProtocol));
        Assert.assertTrue(s.isInScope(url, diffDomain));
        Assert.assertTrue(s.isInScope(url, diffPort));
        Assert.assertTrue(s.isInScope(url, sameSite));
        Assert.assertTrue(s.isInScope(url, noSchemeDiffDomain));

        // Protocol
        s = new URLCrawlScopeStrategy();
        s.setStayOnProtocol(true);
        Assert.assertFalse(s.isInScope(url, diffProtocol));
        Assert.assertTrue(s.isInScope(url, diffDomain));
        Assert.assertTrue(s.isInScope(url, diffPort));
        Assert.assertTrue(s.isInScope(url, sameSite));
        Assert.assertTrue(s.isInScope(url, noSchemeDiffDomain));

        // Domain
        s = new URLCrawlScopeStrategy();
        s.setStayOnDomain(true);
        Assert.assertTrue(s.isInScope(url, diffProtocol));
        Assert.assertFalse(s.isInScope(url, diffDomain));
        Assert.assertTrue(s.isInScope(url, diffPort));
        Assert.assertTrue(s.isInScope(url, sameSite));
        Assert.assertFalse(s.isInScope(url, noSchemeDiffDomain));

        // Port
        s = new URLCrawlScopeStrategy();
        s.setStayOnPort(true);
        Assert.assertFalse(s.isInScope(url, diffProtocol)); // https = 443
        Assert.assertTrue(s.isInScope(url, diffDomain));
        Assert.assertFalse(s.isInScope(url, diffPort));
        Assert.assertTrue(s.isInScope(url, sameSite));
        Assert.assertTrue(s.isInScope(url, noSchemeDiffDomain));


        // Protocol + Domain + Port
        s = new URLCrawlScopeStrategy();
        s.setStayOnProtocol(true);
        s.setStayOnDomain(true);
        s.setStayOnPort(true);
        Assert.assertFalse(s.isInScope(url, diffProtocol));
        Assert.assertFalse(s.isInScope(url, diffDomain));
        Assert.assertFalse(s.isInScope(url, diffPort));
        Assert.assertTrue(s.isInScope(url, sameSite));
        Assert.assertFalse(s.isInScope(url, noSchemeDiffDomain));
    }

    @Test
    public void testStayOnDomainDepthStrategy() throws IOException {
        String sub0 = "http://example.com/test0.html";
        String sub1 = "http://sub1.example.com/test1.html";
        String sub2 = "http://sub2.sub1.example.com/test2.html";
        String sub3 = "http://sub3.sub2.sub1.example.com/test3.html";
        String sub4 = "http://different.com/testd.html";

        URLCrawlScopeStrategy s = new URLCrawlScopeStrategy();

        // Same or not
        s.setStayOnDomain(false);
        Assert.assertTrue(s.isInScope(sub0, sub1));
        Assert.assertTrue(s.isInScope(sub1, sub2));
        Assert.assertTrue(s.isInScope(sub0, sub3));
        Assert.assertTrue(s.isInScope(sub1, sub3));
        Assert.assertTrue(s.isInScope(sub2, sub2));
        Assert.assertTrue(s.isInScope(sub3, sub1));
        Assert.assertTrue(s.isInScope(sub1, sub4));

        // Same or any sub-domain
        s.setStayOnDomain(true);
        s.setIncludeSubdomains(true);
        Assert.assertTrue(s.isInScope(sub0, sub1));
        Assert.assertTrue(s.isInScope(sub1, sub2));
        Assert.assertTrue(s.isInScope(sub0, sub3));
        Assert.assertTrue(s.isInScope(sub1, sub3));
        Assert.assertTrue(s.isInScope(sub2, sub2));
        Assert.assertFalse(s.isInScope(sub3, sub1));
        Assert.assertFalse(s.isInScope(sub1, sub4));
    }
}
