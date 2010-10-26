/*
 ************************************************************************************
 * Copyright (C) 2001-2010 encuestame: system online surveys Copyright (C) 2010
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */

package org.encuestame.business.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.encuestame.business.service.imp.ITwitterService;
import org.encuestame.persistence.domain.security.SecUserTwitterAccounts;
import org.springframework.stereotype.Service;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.http.AccessToken;
import twitter4j.http.RequestToken;

/**
 * Twitter Service.
 * @author Picado, Juan juan@encuestame.org
 * @since Feb 13, 2010 4:07:03 PM
 * @version $Id$
 */
@Service
public class TwitterService extends AbstractBaseService implements ITwitterService {


    private String tinyApi;

    private Log log = LogFactory.getLog(this.getClass());

    /**
     * Constructor.
     */
    public TwitterService() {
    }

    /**
     * Get Tiny Url.
     * @param url survey url
     * @return tiny url
     * @throws IOException IOException
     * @throws HttpException HttpExceptio
     */
    public String getTinyUrl(final String url) throws HttpException, IOException{
        final HttpClient httpclient = new HttpClient();
        final HttpMethod method = new GetMethod(tinyApi);
        method.setQueryString(new NameValuePair[]{new NameValuePair("url",url)});
        httpclient.executeMethod(method);
        final String tinyUrl = method.getResponseBodyAsString();
        method.releaseConnection();
        return tinyUrl;
    }

    /**
     * OAuth Public Tweet.
     * @param secUserTwitterAccount
     * @param tweet
     * @return
     * @throws TwitterException
     */
    public Status publicTweet(final SecUserTwitterAccounts secUserTwitterAccount, final String tweet) throws TwitterException{
        log.debug("publicTweet");
        //Twitter twitter = new TwitterFactory().getInstance();
        log.debug("publicTweet Before  Token  {"+secUserTwitterAccount.getToken());
        log.debug("publicTweet Before Secret Token  {"+secUserTwitterAccount.getSecretToken());
        final AccessToken accessToken = this.createNewOAuthAccessToken(secUserTwitterAccount);
        log.debug("Access Token "+accessToken);
        final Twitter twitter = this.getOAuthAuthorizedInstance(secUserTwitterAccount, accessToken);
        log.debug("Verify  "+twitter.verifyCredentials());
        log.debug("Update Status "+tweet);
        return twitter.updateStatus(tweet);
    }

    /**
     * Create New OAuth Access Token.
     * @param secUserTwitterAccount {@link SecUserTwitterAccounts}.
     * @return {@link AccessToken}.
     */
    public AccessToken createNewOAuthAccessToken(final SecUserTwitterAccounts secUserTwitterAccount){
         final AccessToken accessToken = new AccessToken(secUserTwitterAccount.getToken(), secUserTwitterAccount.getSecretToken());
         return accessToken;
    }

    /**
     * Get OAuthorized Token.
     * @param secUserTwitterAccount {@link SecUserTwitterAccounts}.
     * @return {@link Twitter}.
     */
    public Twitter getOAuthAuthorizedInstance(final SecUserTwitterAccounts secUserTwitterAccount, final AccessToken accessToken){
         return new TwitterFactory().getOAuthAuthorizedInstance(secUserTwitterAccount.getConsumerKey(),
                 secUserTwitterAccount.getConsumerSecret(),
                 accessToken);
    }

    /**
     * Verify Credentials.
     * @param username username
     * @param password password
     * @return {@link User}
     * @throws TwitterException exception
     */
    @Deprecated
    public User verifyCredentials(final String username, final String password) throws TwitterException{
        log.debug("verifyCredentials");
        final Twitter twitter = new TwitterFactory().getInstance(username, password);
        return twitter.verifyCredentials();
    }

    /**
     * Get Twitter Ping.
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     * @return {@link RequestToken}
     * @throws TwitterException exception
     */
    public RequestToken getTwitterPing(String consumerKey, String consumerSecret)
            throws TwitterException {
        if (consumerKey == null) {
            throw new IllegalArgumentException("Consumer key is missing");
        }
        if (consumerSecret == null) {
            throw new IllegalArgumentException("Consumer secret is missing");
        }
        final Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        return twitter.getOAuthRequestToken();
    }

    /**
     * @return the tinyApi
     */
    public String getTinyApi() {
        return tinyApi;
    }

    /**
     * @param tinyApi the tinyApi to set
     */
    public void setTinyApi(final String tinyApi) {
        this.tinyApi = tinyApi;
    }
}