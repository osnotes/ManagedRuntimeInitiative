/*
 * Copyright 1998 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/* @test
   @bug 4091757
   @summary Basic test for setReadOnly method
 */

import java.io.*;


public class SetReadOnly {

    public static void main(String[] args) throws Exception {
        File d = new File(System.getProperty("test.dir", "."));
        File f = new File(d, "x.SetReadOnly");

        if (f.exists()) {
            if (!f.delete())
                throw new Exception("Can't delete test file " + f);
        }
        if (f.setReadOnly())
            throw new Exception("Succeeded on non-existent file: " + f);

        OutputStream o = new FileOutputStream(f);
        o.write('x');
        o.close();
        if (!f.setReadOnly())
            throw new Exception(f + ": Failed on file");
        if (f.canWrite())
            throw new Exception(f + ": File is writeable");

        f = new File(d, "x.SetReadOnly.dir");
        if (f.exists()) {
            if (!f.delete())
                throw new Exception("Can't delete test directory " + f);
        }
        if (!f.mkdir())
            throw new Exception(f + ": Cannot create directory");
        if (!f.setReadOnly())
            throw new Exception(f + ": Failed on directory");
        if (f.canWrite())
            throw new Exception(f + ": Directory is writeable");
        if (!f.delete())
            throw new Exception(f + ": Cannot delete directory");

    }

}
