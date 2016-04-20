package eu.alfred.api.personalization.client.eventrecommendation;

import com.google.gson.annotations.Expose;

import java.util.HashMap;

import eu.alfred.api.personalization.model.eventrecommendation.Event;

/**
 * Created by thardes on 19/04/2016.
 */
public class EventrecommendationDto {
    @Expose
    public HashMap<Event,Integer> id;
}

                            //Not needed at the moment - maybe in the future?!
                            /*  Log.Instance.Info("Checking settings.... ");
                              if (Settings.Instance.MonitoredDirectories == null || Settings.Instance.TutorialDone == false || Settings.Instance.MonitoredDirectories.Count == 0)
                              {
                                  Log.Instance.Info("Tutorial started");
                                  var mainWindow = new Tutorial();
                                  Current.MainWindow = mainWindow;
                                  mainWindow.Show();
                                  Log.Instance.Info("Tutorial Done");
                              }
                              else
                              {*/