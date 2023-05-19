
package versionControl;

import java.sql.Connection;

public interface IVersionControl {

    void Snap(Connection database);
}
