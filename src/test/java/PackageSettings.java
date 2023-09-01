import org.approvaltests.core.ApprovalFailureReporter;
import org.approvaltests.reporters.JunitReporter;

    /**
     * Configure the reporter used by Approval Tests.
     */
    public class PackageSettings {
        public static ApprovalFailureReporter UseReporter         = JunitReporter.INSTANCE;
    }

