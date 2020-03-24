/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Entity.Role;
import Entity.Worker;
import java.util.List;

/**
 *
 * @author ANH DUC
 */
public interface WorkerInterface {

    List<Role> getAllRole() throws Exception;

    List<Worker> getAllWorker() throws Exception;

    Worker getDetailWorker(int id) throws Exception;

    boolean updateWorker(Worker worker) throws Exception;

    boolean addWorker(Worker worker) throws Exception;

}
