package com.redwood.rottenpotato.services;

import com.redwood.rottenpotato.models.Movie;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MovieValidationService
{
    public boolean validMovie(String movieName, String movieDate, double rate, double boxOffice)
    {
        //Check if the movie is valid
        try
        {
            DateFormat formatter;
            Date date1;
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            date1 = formatter.parse(movieDate);

            if(rate < 0 || boxOffice <0 )
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        //If there is a parse exception, then return false
        catch(ParseException e)
        {
            return false;
        }
    }

}
