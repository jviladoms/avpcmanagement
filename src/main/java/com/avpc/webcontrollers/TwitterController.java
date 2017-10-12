package com.avpc.webcontrollers;

import javax.inject.Inject;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/twitter")
public class TwitterController {

   private Twitter twitter;

   private ConnectionRepository connectionRepository;

   @Inject
   public TwitterController(Twitter twitter, ConnectionRepository connectionRepository) {
      this.twitter = twitter;
      this.connectionRepository = connectionRepository;
   }

   @RequestMapping(method= RequestMethod.GET)
   public String helloTwitter(Model model) {
      if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
         return "redirect:/connect/twitter";
      }

      model.addAttribute(twitter.userOperations().getUserProfile());
      CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
      model.addAttribute("friends", friends);
      return "hello";
   }

}
