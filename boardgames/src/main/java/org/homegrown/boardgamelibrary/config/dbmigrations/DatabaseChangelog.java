package org.homegrown.boardgamelibrary.config.dbmigrations;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by JoLe on 20/03/2017.
 */
@ChangeLog
public class DatabaseChangelog {

    private final Logger log = LoggerFactory.getLogger(DatabaseChangelog.class);


    @ChangeSet(order = "001", id = "ChangeSet-1", author = "jhipster")
    public void someChange(DB db) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(new File(getClass().getClassLoader().getResource("eclipse.json").getFile())));

        DBCollection boardgame = db.getCollection("BOARDGAME");
        BasicDBObject eclipse = new BasicDBObject().parse(obj.toString());
        boardgame.insert(eclipse);
    }

}
