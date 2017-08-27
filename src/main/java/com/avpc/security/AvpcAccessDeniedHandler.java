package com.avpc.security;

import java.io.IOException;

import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class AvpcAccessDeniedHandler implements AccessDeniedHandler {

   public static final Logger LOG
       = Logger.getLogger(AvpcAccessDeniedHandler.class.getName());

   @Override
   public void handle(
       HttpServletRequest request,
       HttpServletResponse response,
       AccessDeniedException exc) throws IOException, ServletException {

      Authentication auth
          = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null) {
         LOG.warning("User: " + auth.getName()
             + " attempted to access the protected URL: "
             + request.getRequestURI());
      }

      response.sendRedirect(request.getContextPath() + "/accessDenied");
   }
}