import java.util.*;

//Problem 11: Company Asset Tracker (Map<String, Set<Asset>>)
//Store assets per department with unique Asset objects.
//        •	Detect duplicate asset IDs globally.

// Asset class to represent each asset with ID and name
class Asset {
    private String assetId;
    private String assetName;

    public Asset(String assetId, String assetName) {
        this.assetId = assetId;
        this.assetName = assetName;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getAssetName() {
        return assetName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Asset other = (Asset) obj;
        return assetId.equals(other.assetId); // only ID matters for equality
    }

    @Override
    public int hashCode() {
        return assetId.hashCode(); // only ID matters for uniqueness in Set
    }

    @Override
    public String toString() {
        return assetId + " - " + assetName;
    }
}

public class q11 {
    // Map to store assets by department
    private Map<String, Set<Asset>> departmentAssets;

    // Set to keep track of all global asset IDs
    private Set<String> globalAssetIds;

    public q11() {
        departmentAssets = new HashMap<>();
        globalAssetIds = new HashSet<>();
    }

    // Method to add asset to department
    public void addAsset(String department, Asset asset) {
        // Check for duplicate asset ID globally
        if (globalAssetIds.contains(asset.getAssetId())) {
            System.out.println("Duplicate Asset ID Detected: " + asset.getAssetId());
            return;
        }

        // If department doesn't exist, create a new set
        if (!departmentAssets.containsKey(department)) {
            departmentAssets.put(department, new HashSet<Asset>());
        }

        // Add asset to department's set
        Set<Asset> assets = departmentAssets.get(department);
        assets.add(asset);

        // Add asset ID to global set
        globalAssetIds.add(asset.getAssetId());

        System.out.println("Asset added to " + department + ": " + asset);
    }

    // Print all assets by department
    public void printAllAssets() {
        for (String department : departmentAssets.keySet()) {
            System.out.println("Department: " + department);
            Set<Asset> assets = departmentAssets.get(department);
            for (Asset asset : assets) {
                System.out.println(asset);
            }
        }
    }

    // Main method to test
    public static void main(String[] args) {
        q11 tracker = new q11();

        tracker.addAsset("IT", new Asset("A001", "Laptop"));
        tracker.addAsset("Finance", new Asset("A002", "Calculator"));
        tracker.addAsset("HR", new Asset("A003", "Printer"));
        tracker.addAsset("IT", new Asset("A004", "Router"));
        tracker.addAsset("Finance", new Asset("A002", "Scanner")); // Duplicate ID

        System.out.println();
        tracker.printAllAssets();
    }
}
