package com.pushtorefresh.storio.sqlite.operations.delete;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collections;
import java.util.Set;

import static com.pushtorefresh.storio.internal.Checks.checkNotEmpty;
import static com.pushtorefresh.storio.internal.Checks.checkNotNull;
import static java.util.Collections.singleton;
import static java.util.Collections.unmodifiableSet;

/**
 * Immutable container for result of Delete Operation.
 * <p>
 * Instances of this class are Immutable.
 */
public final class DeleteResult {

    private final int numberOfRowsDeleted;

    @NonNull
    private final Set<String> affectedTables;

    @NonNull
    private final Set<String> affectedTags;

    private DeleteResult(
            int numberOfRowsDeleted,
            @NonNull Set<String> affectedTables,
            @Nullable Set<String> affectedTags
    ) {
        checkNotNull(affectedTables, "Please specify affected tables");

        for (String table : affectedTables) {
            checkNotEmpty(table, "affectedTable must not be null or empty, affectedTables = " + affectedTables);
        }

        if (affectedTags != null) {
            for (String tag : affectedTags) {
                checkNotEmpty(tag, "affectedTag must not be null or empty, affectedTags = " + affectedTags);
            }
        }

        this.numberOfRowsDeleted = numberOfRowsDeleted;
        this.affectedTables = Collections.unmodifiableSet(affectedTables);
        this.affectedTags = affectedTags == null ? Collections.<String>emptySet() : unmodifiableSet(affectedTags);
    }

    /**
     * Creates new instance of immutable container for results of Delete Operation.
     *
     * @param numberOfRowsDeleted number of rows that were deleted.
     * @param affectedTables      tables that were affected.
     * @param affectedTags        notification tags that were affected.
     * @return new instance of immutable container for result of Delete Operation.
     */
    @NonNull
    public static DeleteResult newInstance(
            int numberOfRowsDeleted,
            @NonNull Set<String> affectedTables,
            @Nullable Set<String> affectedTags
    ) {
        return new DeleteResult(numberOfRowsDeleted, affectedTables, affectedTags);
    }

    /**
     * Creates new instance of immutable container for results of Delete Operation.
     *
     * @param numberOfRowsDeleted number of rows that were deleted.
     * @param affectedTables      tables that were affected.
     * @return new instance of immutable container for result of Delete Operation.
     */
    @NonNull
    public static DeleteResult newInstance(int numberOfRowsDeleted, @NonNull Set<String> affectedTables) {
        return newInstance(numberOfRowsDeleted, affectedTables, null);
    }

    /**
     * Creates new instance of immutable container for results of Delete Operation.
     *
     * @param numberOfRowsDeleted number of rows that were deleted.
     * @param affectedTable       table that was affected.
     * @param affectedTag         notification tag that was affected.
     * @return new instance of immutable container for results of Delete Operation.
     */
    @NonNull
    public static DeleteResult newInstance(
            int numberOfRowsDeleted,
            @NonNull String affectedTable,
            @Nullable String affectedTag
    ) {
        checkNotNull(affectedTable, "Please specify affected table");
        final Set<String> tags = affectedTag == null ? null : singleton(affectedTag);
        return newInstance(numberOfRowsDeleted, Collections.singleton(affectedTable), tags);
    }

    /**
     * Creates new instance of immutable container for results of Delete Operation.
     *
     * @param numberOfRowsDeleted number of rows that were deleted.
     * @param affectedTable       table that was affected.
     * @return new instance of immutable container for results of Delete Operation.
     */
    @NonNull
    public static DeleteResult newInstance(int numberOfRowsDeleted, @NonNull String affectedTable) {
        return newInstance(numberOfRowsDeleted, affectedTable, null);
    }

    /**
     * Gets number of rows that were deleted.
     *
     * @return number of rows that were deleted.
     */
    public int numberOfRowsDeleted() {
        return numberOfRowsDeleted;
    }

    /**
     * Gets names of the tables that wer affected by Delete Operation.
     *
     * @return unmodifiable set of tables that were affected.
     */
    @NonNull
    public Set<String> affectedTables() {
        return affectedTables;
    }

    /**
     * Gets notification tags which were affected by Delete Operation.
     *
     * @return non-null unmodifiable set of affected tags.
     */
    @NonNull
    public Set<String> affectedTags() {
        return affectedTags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteResult that = (DeleteResult) o;

        if (numberOfRowsDeleted != that.numberOfRowsDeleted) return false;
        if (!affectedTables.equals(that.affectedTables)) return false;
        return affectedTags.equals(that.affectedTags);

    }

    @Override
    public int hashCode() {
        int result = numberOfRowsDeleted;
        result = 31 * result + affectedTables.hashCode();
        result = 31 * result + affectedTags.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DeleteResult{" +
                "numberOfRowsDeleted=" + numberOfRowsDeleted +
                ", affectedTables=" + affectedTables +
                ", affectedTags=" + affectedTags +
                '}';
    }
}
