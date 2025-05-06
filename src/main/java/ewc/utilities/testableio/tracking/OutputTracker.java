/*
 * MIT License
 *
 * Copyright (c) 2025 Eugene Terekhov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ewc.utilities.testableio.tracking;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * I am responsible for tracking all the queries made by the testable instances.
 *
 * @since 0.3
 */
public class OutputTracker<T> {
    private final List<T> data = Collections.synchronizedList(new LinkedList<>());
    private final OutputListener<T> listener;

    OutputTracker(final OutputListener<T> listener) {
        this.listener = listener;
    }

    public List<T> data() {
        return List.copyOf(this.data);
    }

    public List<T> clear() {
        final List<T> cleared = this.data();
        this.data.clear();
        return cleared;
    }

    void track(final T output) {
        this.data.add(output);
    }

    List<T> stopTracking() {
        this.listener.stopTracking(this);
        return this.clear();
    }
}
