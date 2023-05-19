package versionControl;

public class Factory {

    public static IVersionControl GetSnapShot(String typeFile){

        switch(typeFile) {
            case "EXPORT":
                return new Export();
            case "VERSION":
                return new DatabaseVersionControl();
        }


        return null;


    }
}
